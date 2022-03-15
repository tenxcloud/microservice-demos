using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace NetCoreDemoB.Controllers
{
    [ApiController]
    public class DemoBController : ControllerBase
    {

        private readonly ILogger<DemoBController> _logger;

        public DemoBController(ILogger<DemoBController> logger)
        {
            _logger = logger;
        }
        
        [HttpGet("demo/test/ip")]
        public string TestIP()
        {
            string str = System.Net.Dns.GetHostAddresses(System.Net.Dns.GetHostName()).GetValue(0).ToString();
            Console.WriteLine("B服务Console输出：" + str);
            return str + "\n";
        }

        [HttpGet("demob/test")]
        public string Test()
        {
            var rng = new Random();
            string str = "外部调用服务DemoB: " + DateTime.Now.ToString() + "——" + Guid.NewGuid().ToString();
            Console.WriteLine("B服务Console输出：" + str);
            return str;
        }

        [HttpGet("demob/testoutb")]
        public string TestOutB()
        {
            var rng = new Random();
            string str = "服务DemoB被A调用";
            Console.WriteLine("B服务Console输出：" + str);
            return str;
        }
    }
}
