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

    def sendPost(self,url,headers,data):
        head, req = url.split('/', 1)
        req = '/' + req
        for h in headers:
            h = '%s\r\n', %h
        headers[-1] = headers[-1] + '\r\n'
        commandTab = [
        'at+cipstart="TCP","%s","80"\r\n' %head,
        'at+cipsend\r\n',
        'POST %s HTTP/1.1\r\n' % req,
        'Host: %s\r\n' % head,
        'Content-Length: %d\r\n' % len(data)] + headers + [
        '%s\r\n' %data,
        str(chr(26)),
        'at+cipclose'
        ]
        self.console.isOpen()
        time.sleep(0.5)
        for com in commandTab:
            self.console.write(str.encode(com))
            self.reader(2,2)


gprs = At_command()
#gprs.config()
gprs.sendGet('student.agh.edu.pl/~cvmorgen/skrypt_ipki/index.php?ip=GETdupa')
gprs.sendPost('student.agh.edu.pl/~cvmorgen/skrypt_ipki/index.php',['Content-Type: application/x-www-form-urlencoded'],'ip=DUPOWY_POST_RULEZ')


