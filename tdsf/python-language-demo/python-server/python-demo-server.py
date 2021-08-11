from skywalking import agent, config
import os

config.init(collector=os.getenv("OAP_SERVER"), service='python-server')
config.flask_collect_http_params = True
agent.start()

from flask import Flask

app = Flask(__name__)


@app.route('/')
def hello_world():
    return 'Hello World___'+os.getenv("PAAS_VERSION", "v1")


if __name__ == '__main__':
    app.run(host="0.0.0.0", port=8082, debug="DEBUG")
