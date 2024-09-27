import threading
import aiohttp
import asyncio
import time
from aiohttp import ClientTimeout, TCPConnector

NUM_REQUESTS = 1000
URL = 'http://localhost:8080/send'
MESSAGE = 'Mensagem Teste'
NUM_MESSAGE = 75000

async def send_post(session, message, index):
    data = f'{message} {index}'
    
    try:
        async with session.post(URL, json=data) as response:
            return await response.text()
    except Exception as e:
        print(f"Erro na requisição {index}: {e}")

async def send_requests():
    timeout = ClientTimeout(total=60)
    connector = TCPConnector(limit_per_host=NUM_MESSAGE)

    async with aiohttp.ClientSession(timeout=timeout, connector=connector) as session:
        tasks = []
        for i in range(NUM_REQUESTS):
            tasks.append(send_post(session, MESSAGE, i))

            if len(tasks) >= NUM_MESSAGE:
                await asyncio.gather(*tasks)
                tasks = []

        if tasks:
            await asyncio.gather(*tasks)

def run_requests():
    start_time = time.time()
    
    asyncio.run(send_requests())
    
    total_time = time.time() - start_time
    print(f"Tempo total para {NUM_REQUESTS} requisições: {total_time:.2f} segundos")

thread = threading.Thread(target=run_requests)
thread.start()

thread.join()