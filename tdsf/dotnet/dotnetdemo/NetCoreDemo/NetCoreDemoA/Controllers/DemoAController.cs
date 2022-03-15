using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;

namespace NetCoreDemoA.Controllers
{
    [ApiController]
    public class DemoAController : ControllerBase
    {
        private readonly ILogger<DemoAController> _logger;

        public DemoAController(ILogger<DemoAController> logger)
        {
            _logger = logger;
        }

        [HttpGet("demo/test/ip")]
        public string TestIP()
        {
            string str = System.Net.Dns.GetHostAddresses(System.Net.Dns.GetHostName()).GetValue(0).ToString();
            Console.WriteLine("A服务Console输出：" + str);
            return str + "\n";
        }

        [HttpGet("demoa/test")]
        public string Test()
        {
            var rng = new Random();
            string str = "外部调用服务DemoA: " + DateTime.Now.ToString() + "——" + Guid.NewGuid().ToString();
            Console.WriteLine("A服务Console输出：" + str);
            return str;
        }

        [HttpGet("demoa/testatob")]
        public string TestAtoB(string ipport)
        {
            string _url = string.Format("http://{0}/demob/testoutb", ipport);
            Console.WriteLine("A服务Console输出http请求调用B的URL地址：" + _url);

            string outStr = GetData(_url);
            string str = "A调用服务DemoB: " + "——B返回结果：" + outStr;
            Console.WriteLine("A服务Console输出http请求调用B的返回结果：" + outStr);
            return str;
        }

        [HttpGet("demoa/testnettojavaa")]
        public string TestNetToJavaA(string ipport)
        {
            string _url = string.Format("http://{0}/demoa/hello", ipport);
            Console.WriteLine("A服务Console输出http请求调用JavaA的URL地址：" + _url);

            string outStr = GetData(_url);
            string str = "A服务调用JavaA: " + "——Java返回结果：" + outStr;
            Console.WriteLine("A服务Console输出http请求调用JavaA的返回结果：" + outStr);
            return str;
        }

        [HttpGet("demoa/testnettojavab")]
        public string TestNetToJavaB(string ipport)
        {
            string _url = string.Format("http://{0}/demob/hello", ipport);
            Console.WriteLine("A服务Console输出http请求调用JavaB的URL地址：" + _url);

            string outStr = GetData(_url);
            string str = "A服务调用JavaB: " + "——Java返回结果：" + outStr;
            Console.WriteLine("A服务Console输出http请求调用JavaB的返回结果：" + outStr);
            return str;
        }



        private String GetData(String url)
        {
            HttpWebRequest request = (HttpWebRequest)WebRequest.Create(url);
            string result = "";
            try
            {
                request.Method = "GET";
                request.ContentType = "text/html, application/xhtml+xml, */*";

                HttpWebResponse response = (HttpWebResponse)request.GetResponse();
                Stream rs = response.GetResponseStream();
                StreamReader sr = new StreamReader(rs, Encoding.UTF8);
                result = sr.ReadToEnd();
                sr.Close();
                rs.Close();
            }
            catch (Exception ex)
            {
                result = ex.ToString();
                throw;
            }
            return result;
        }

    }
}
