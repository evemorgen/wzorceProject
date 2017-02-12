import os
from tornado.httpclient import AsyncHTTPClient


class Service():
    def __init__(self, host, port, ifc_list):
        self.host = host
        self.port = port
        self.ifc_list = ifc_list
        self.observer_host = os.environ['OBSERVER_IP']
        self.observer_port = os.environ['OBSERVER_PORT']
        self.http_client = AsyncHTTPClient()

    def get_id(self, response):
        print(response)

    def register_interface(self):
        self.http_client.fetch("http://www.google.com/", self.get_id)

    def unregister_interface(self):
        if self.ifc_id is None:
            raise Exception('Cannot unregister, interface not registred')
