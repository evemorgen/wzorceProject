import serial
import time

class At_command:
    def __init__(self):
        self.console = serial.Serial(
        port='/dev/ttyAMA0',
        baudrate=9600,
        )

    def reader(self,t,tt):
        out = ''
        time.sleep(t)
        while self.console.inWaiting() > 0:
            out += self.console.read(1).decode()
        if out != '':
            print (">>" + out)
        time.sleep(tt)

    def config(self):
        commandTab = [
        'at+cfun=1,1\r\n',
        'at+creg=1\r\n',
        'at+cgatt=1\r\n',
        'at+cstt="internet.cp","",""\r\n',
        'at+ciicr\r\n',
        'at+cifsr\r\n', 
        'at+cmgf=1\r\n'
        ]
        self.console.isOpen()
        time.sleep(0.5)
        for com in commandTab:
            self.console.write(str.encode(com))
            self.reader(2,8)

    def sendGet(self,url):
        head, req = url.split('/', 1)
        req = '/' + req
        commandTab = [
        'at+cipstart="TCP","%s","80"\r\n' %head,
        'at+cipsend\r\n',
        'GET %s HTTP/1.0\r\n\r\n' %req,
        str(chr(26))
        ]
        self.console.isOpen()
        time.sleep(0.5)
        for com in commandTab:
            self.console.write(str.encode(com))
            self.reader(2,2)

    def sendPost(self,url):
        head, req = url.split('/', 1)
        req = '/' + req
        req, params = req.split('?', 1)
        commandTab = [
        'at+cipstart="TCP","%s","80"\r\n' %head,
        'at+cipsend\r\n',
        'POST %s HTTP/1.1\r\n' % req,
        '%s\r\n\r\n' %params,
        str(chr(26))
        ]
        self.console.isOpen()
        time.sleep(0.5)
        for com in commandTab:
            self.console.write(str.encode(com))
            self.reader(2,2)


gprs = At_command()
#gprs.config()
gprs.sendGet('student.agh.edu.pl/~cvmorgen/skrypt_ipki/index.php?ip=GETdupa')
gprs.sendPost('student.agh.edu.pl/~cvmorgen/skrypt_ipki/index.php?ip=POSTdupa')


