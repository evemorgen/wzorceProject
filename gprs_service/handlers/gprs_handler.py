import logging
import json

from tornado.web import RequestHandler
from tornado.gen import coroutine


class GprsHandler(RequestHandler):

    def initialize(self, at):
        self.at = at
        self.methods = {
            'conf': at.config,
            'get': at.send_get,
            'post': at.send_get,
            'sms': at.send_sms
        }

    def set_default_headers(self):
        self.set_header("Access-Control-Allow-Origin", "*")
        self.set_header("Access-Control-Allow-Headers", "Content-Type")
        self.set_header('Access-Control-Allow-Methods', 'POST')

    @coroutine
    def post(self):
        logging.info('post in gprs handler')
        params = json.loads(self.request.body.decode('utf-8'))
        if 'method' in params:
            if params['method'] == 'conf':
                result = self.methods[params['method']]()
            if params['method'] == 'get':
                result = self.methods[params['method']](params['url'])
            if params['method'] == 'post':
                result = self.methods[params['method']](params['url'], params['headers'], params['data'])
            if params['method'] == 'sms':
                result = self.methods[params['method']](params['number'], params['text'])
        self.write(json.dumps(result))

    def options(self):
        self.set_status(204)
        self.finish()
