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

    def reader(self, t, tt, result):
        out = ''
        time.sleep(t)
        while self.console.inWaiting() > 0:
            out += self.console.read(1).decode()
        if out != '':
            if 'ERROR' in out:
                self.status = 'ERROR'
                print ("ERROR")
                logging.info(self.status)
            print (">>" + out)
            logging.info(out)
            logging.info(self.status)
            result = result.append(out)
        time.sleep(tt)
        return result

    def config(self):
        result = []
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
            out = self.reader(2, 8, result)
            if out is not None:
                result = out
        return {"status": self.status, "result": result}

    def send_get(self, url):
        result = []
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
            out = self.reader(2, 2, result)
            if out is not None:
                result = out
        return {"status": self.status, "result": result}

    def send_post(self, url, headers, data):
        result = []
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
            str(chr(26))
        ]
        self.console.isOpen()
        time.sleep(0.5)
        for com in command_tab:
            self.console.write(str.encode(com))
            out = self.reader(2, 2, result)
            if out is not None:
                result = out
        return {"status": self.status, "result": result}

    def send_sms(self, number, txt):
        result = []
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
            logging.info('%s - %s', com, result)
            self.console.write(str.encode(com))
            out = self.reader(2, 2, result)
            if out is not None:
                result = out
        return {"status": self.status, "result": result}
