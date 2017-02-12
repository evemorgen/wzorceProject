import logging
import json

from tornado.web import RequestHandler
from tornado.gen import coroutine

from utils import Config


class MainHandler(RequestHandler):

    def initialize(self, coords):
        self.config = Config()
        self.coords = coords
        self.methods = {
            'get_raw_frames': coords.get_raw_frames,
            'get_sat_info': coords.get_sat_info,
            'get_pos_info': coords.get_pos_info,
            'get_all_data': coords.get_all_data
        }

    def set_default_headers(self):
        self.set_header("Access-Control-Allow-Origin", "*")
        self.set_header("Access-Control-Allow-Headers", "Content-Type")
        self.set_header('Access-Control-Allow-Methods', 'POST')

    @coroutine
    def post(self):
        logging.info('post in main handler')
        params = json.loads(self.request.body.decode('utf-8'))
        if 'method' in params:
            if params['method'] == 'get_raw_frames':
                result = self.methods[params['method']](params['n'])
            else:
                result = self.methods[params['method']]()
        self.write(json.dumps(result))

    def options(self):
        self.set_status(204)
        self.finish()
