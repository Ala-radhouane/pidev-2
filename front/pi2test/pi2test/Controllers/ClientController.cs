using pi2.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Web;
using System.Web.Mvc;

namespace pi2test.Controllers
{
    public class ClientController : Controller
    {
        // GET: Client
        public ActionResult Index()
        {
            IEnumerable<Client> client1 = null;
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri("http://localhost:8080/");
                var responseTask = client.GetAsync("retrieve-all-Clients");
                responseTask.Wait();

                var result = responseTask.Result;
                if (result.IsSuccessStatusCode)
                {
                    var readJob = result.Content.ReadAsAsync<IList<Client>>();
                    readJob.Wait();
                    client1 = (IEnumerable<Client>)readJob.Result;

                }
                else
                {
                    client1 = Enumerable.Empty<Client>();
                    ModelState.AddModelError(string.Empty, "Server error occured. Please contact admin for help!");
                }
            }
            return View(client1);
        }

        // GET: Client/Details/5
        public ActionResult Details(int id)
        {
            Client clients = null;

            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri("http://localhost:8080/");
                var responseTask = client.GetAsync("retrieve-Client/" + id.ToString());
                responseTask.Wait();

                var result = responseTask.Result;
                if (result.IsSuccessStatusCode)
                {
                    var readTask = result.Content.ReadAsAsync<Client>();
                    readTask.Wait();

                    clients = readTask.Result;
                }
            }
            return View(clients);
        }

        // GET: Client/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: Client/Create
        [HttpPost]
        public ActionResult Create(Client client1)
        {
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri("http://localhost:8080/");
                var postJob = client.PostAsJsonAsync<Client>("add-Client", client1);
                postJob.Wait();

                var postResult = postJob.Result;
                if (postResult.IsSuccessStatusCode)
                {
                    return RedirectToAction("Index");
                }
                return View(client1);
            }
        }

        
    }
}
