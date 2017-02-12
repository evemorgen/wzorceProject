import logging

from tornado.web import RequestHandler
from tornado.gen import coroutine


class MainHandler(RequestHandler):

    def set_default_headers(self):
        self.set_header("Access-Control-Allow-Origin", "*")
        self.set_header("Access-Control-Allow-Headers", "Content-Type")
        self.set_header('Access-Control-Allow-Methods', 'POST')

    @coroutine
    def post(self):
        logging.info('post in main handler')
        self.write('ok')

    def options(self):
        self.set_status(204)
        self.finish()
