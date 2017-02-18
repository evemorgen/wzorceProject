import serial
import time
import logging


class AtCommand:
    def __init__(self):
        self.console = serial.Serial(
            port='/dev/ttyAMA0',
            baudrate=9600,
        )
        self.status = 'OK'

    def reader(self, t, tt, status):
        out = ''
        time.sleep(t)
        while self.console.inWaiting() > 0:
            out += self.console.read(1).decode()
        if out != '':
            if 'ERROR' in out:
                self.status = 'ERROR'
                print ("ERROR")
            print (">>" + out)
        time.sleep(tt)

    def config(self):
        self.status = 'OK'
        command_tab = [
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
        for com in command_tab:
            self.console.write(str.encode(com))
            self.reader(2, 8)
        return self.status

    def send_get(self, url):
        self.status = 'OK'
        head, req = url.split('/', 1)
        req = '/' + req
        command_tab = [
            'at+cipstart="TCP","%s","80"\r\n' % head,
            'at+cipsend\r\n',
            'GET %s HTTP/1.0\r\n\r\n' % req,
            str(chr(26))
        ]
        self.console.isOpen()
        time.sleep(0.5)
        for com in command_tab:
            self.console.write(str.encode(com))
            self.reader(2, 2)
        return self.status

    def send_post(self, url, headers, data):
        self.status = 'OK'
        head, req = url.split('/', 1)
        req = '/' + req
        at_headers = []
        for hedy in headers:
            at_headers = at_headers + ['%s\r\n' % hedy]

        at_headers[-1] = at_headers[-1] + '\r\n'
        command_tab = [
            'at+cipstart="TCP","%s","80"\r\n' % head,
            'at+cipsend\r\n',
            'POST %s HTTP/1.1\r\n' % req,
            'Host: %s\r\n' % head,
            'Content-Length: %d\r\n' % len(data)] + at_headers + [
            '%s\r\n' % data,
            str(chr(26)),
            'at+cipclose\r\n'
        ]
        self.console.isOpen()
        time.sleep(0.5)
        for com in command_tab:
            self.console.write(str.encode(com))
            self.reader(2, 2)
        return self.status

    def send_sms(self, number, txt):
        self.status = 'OK'
        command_tab = [
            'at+cmgf=1\r\n',
            'at+cmgs="%s"\r\n' % number,
            '%s\r\n' % txt,
            str(chr(26))
        ]
        self.console.isOpen()
        time.sleep(0.5)
        for com in command_tab:
            self.console.write(str.encode(com))
            self.reader(2, 2)
        return self.status

gprs = AtCommand()
gprs.config()
gprs.send_get('student.agh.edu.pl/~cvmorgen/skrypt_ipki/index.php?ip=GETdupa')
gprs.send_post(
    'student.agh.edu.pl/~cvmorgen/skrypt_ipki/index.php',
    ['Content-Type: application/x-www-form-urlencoded'],
    'ip=DUPOWY_POST_RULEZ'
)
#gprs.send_sms(
 #   '+48507861428',
  #  'proces konfiguracji, get oraz post przeszly poprawnie'
#)
