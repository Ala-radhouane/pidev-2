using pi2.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Web;
using System.Web.Mvc;

namespace pi2test.Controllers
{
    public class BasketController : Controller
    {
        // GET: Basket
        public ActionResult Index()
        {

            IEnumerable<Basket> basket = null;
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri("http://localhost:8080/");
                var responseTask = client.GetAsync("retrieve-all-Basket");
                responseTask.Wait();

                var result = responseTask.Result;
                if (result.IsSuccessStatusCode)
                {
                    var readJob = result.Content.ReadAsAsync<IList<Basket>>();
                    readJob.Wait();
                    basket = (IEnumerable<Basket>)readJob.Result;

                }
                else
                {
                    basket = Enumerable.Empty<Basket>();
                    ModelState.AddModelError(string.Empty, "Server error occured. Please contact admin for help!");
                }
            }
            return View(basket);
        }

        // GET: Orders/Create
        public ActionResult Create()
        {
            List<Basket> o = new List<Basket>();
            IEnumerable<Product> products = null;
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri("http://localhost:8080/");
                var responseTask = client.GetAsync("retrieve-all-products");
                responseTask.Wait();

                var result = responseTask.Result;
                if (result.IsSuccessStatusCode)
                {
                    var readJob = result.Content.ReadAsAsync<IList<Product>>();
                    readJob.Wait();
                    products = readJob.Result;
                }
            }

            ViewBag.productList = new SelectList(products, "id_prod", "name_prod");

            IEnumerable<Client> clients = null;
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
                    clients = readJob.Result;
                }
            }

            ViewBag.clientList = new SelectList(clients, "id", "firstname");
            return View();
            
        }

        // POST: Orders/Create
        [HttpPost]
        public ActionResult Create(Basket b)
        {
            string idc = Request.Form["clientList"].ToString();
            Client c = new Client();
            c.id = Convert.ToInt32(idc);
            b.client = c;

            string idprod = Request.Form["productList"].ToString();
            Product p = new Product();
            p.id_prod = Convert.ToInt32(idprod);
            b.product = p;
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri("http://localhost:8080/add-basket");
                var postJob = client.PostAsJsonAsync<Basket>("add-basket", b);

                postJob.Wait();

                var postResult = postJob.Result;
                if (postResult.IsSuccessStatusCode)
                {

                    return RedirectToAction("Index");
                }
                ModelState.AddModelError(string.Empty, "Server error occured. Please contact admin for help!");
                // ViewBag.id_prod = new SelectList(products, "id_cat", "name_cat");
                return View(b);
            }
        }

        public ActionResult Details(int id)
        {
            Basket baskets = null;

            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri("http://localhost:8080/");
                var responseTask = client.GetAsync("retrieve-Basket/" + id.ToString());
                responseTask.Wait();

                var result = responseTask.Result;
                if (result.IsSuccessStatusCode)
                {
                    var readTask = result.Content.ReadAsAsync<Basket>();
                    readTask.Wait();

                    baskets = readTask.Result;
                }
            }
            return View(baskets);
        }
    }
}
