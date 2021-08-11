from skywalking import agent, config
import requests
import os

config.init(collector=os.getenv("OAP_SERVER"), service='python-client')
config.flask_collect_http_params = True
agent.start()

from flask import Flask

app = Flask(__name__)


@app.route('/')
def hello_world():
    res = requests.get("http://python-server:8082")
    return res.content


if __name__ == '__main__':
    app.run(host="0.0.0.0", port=8081, debug="DEBUG")
