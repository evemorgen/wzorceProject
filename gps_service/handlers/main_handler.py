import logging

from tornado.web import RequestHandler
from tornado.gen import coroutine


class MainHandler(RequestHandler):

    def initialize(self, coords):
        self.coords = coords

    def set_default_headers(self):
        self.set_header("Access-Control-Allow-Origin", "*")
        self.set_header("Access-Control-Allow-Headers", "Content-Type")
        self.set_header('Access-Control-Allow-Methods', 'POST')

    @coroutine
    def post(self):
        logging.info('post in main handler')
        self.write(str(self.coords.frames_list))

    def options(self):
        self.set_status(204)
        self.finish()
