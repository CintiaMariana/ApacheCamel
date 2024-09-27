import requests

def send_message(message):
    url = 'http://localhost:8080/send'
    headers = {'Content-Type': 'text/plain'}
    response = requests.post(url, headers=headers, data=message)
    
    if response.status_code == 200:
        print('Mensagem enviada com sucesso:', message)
    else:
        print('Falha ao enviar a mensagem:', response.text)

if __name__ == '__main__':
    while True:
        message = input("Digite a mensagem para enviar (ou 'sair' para encerrar): ")
        if message.lower() == 'sair':
            break
        send_message(message)