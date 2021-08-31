using pi2.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Web;
using System.Web.Mvc;

namespace pi2test.Controllers
{
    public class OrdersController : Controller
    {
        // GET: Orders
        public ActionResult Index()
        {
            IEnumerable<Orders> orders = null;
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri("http://localhost:8080/");
                var responseTask = client.GetAsync("retrieve-all-orders");
            responseTask.Wait();

            var result = responseTask.Result;
            if (result.IsSuccessStatusCode)
            {
                var readJob = result.Content.ReadAsAsync<IList<Orders>>();
                readJob.Wait();
                orders = (IEnumerable<Orders>)readJob.Result;

            }
            else
            {
                orders = Enumerable.Empty<Orders>();
                ModelState.AddModelError(string.Empty, "Server error occured. Please contact admin for help!");
            }
        }
            return View(orders);
        }

        // GET: Orders/Details/5
        public ActionResult Details(int id)
        {
            Orders orders = null;

            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri("http://localhost:8080/");
                var responseTask = client.GetAsync("retrieve-orders/" + id.ToString());
                responseTask.Wait();

                var result = responseTask.Result;
                if (result.IsSuccessStatusCode)
                {
                    var readTask = result.Content.ReadAsAsync<Orders>();
                    readTask.Wait();

                    orders = readTask.Result;
                }
            }
            return View(orders);
        }

        // GET: Orders/Create
        public ActionResult Create()
        {
            List<Orders> o = new List<Orders>();
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
            return View();
        }

        // POST: Orders/Create
        [HttpPost]
        public ActionResult Create(Orders o)
        {
            string idprod = Request.Form["productList"].ToString();
            Product p = new Product();
            p.id_prod = Convert.ToInt32(idprod);
            o.products = p;
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri("http://localhost:8080/add-orders");
                var postJob = client.PostAsJsonAsync<Orders>("add-orders", o);

                postJob.Wait();

                var postResult = postJob.Result;
                if (postResult.IsSuccessStatusCode)
                {

                    return RedirectToAction("Index");
                }
                ModelState.AddModelError(string.Empty, "Server error occured. Please contact admin for help!");
               // ViewBag.id_prod = new SelectList(products, "id_cat", "name_cat");
                return View(o);
            }
        }

        // GET: Orders/Edit/5
        public ActionResult Edit(int id)
        {
            return View();
        }

        // POST: Orders/Edit/5
        [HttpPost]
        public ActionResult Edit(int id, FormCollection collection)
        {
            try
            {
                // TODO: Add update logic here

                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }

        // GET: Orders/Delete/5
        public ActionResult Delete(int id)
        {
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri("http://localhost:8080/");
                var deleteTask = client.DeleteAsync("remove-orders/" + id.ToString());

                var result = deleteTask.Result;
                if (result.IsSuccessStatusCode)
                {
                    return RedirectToAction("Index");
                }
                return RedirectToAction("Index");
            }
        }

        // POST: Orders/Delete/5
        [HttpPost]
        public ActionResult Delete(int id, FormCollection collection)
        {
            try
            {
                // TODO: Add delete logic here

                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }

        public ActionResult Affect(int id)
        {
            Orders orders = null;

            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri("http://localhost:8080/");
                var responseTask = client.GetAsync("retrieve-orders/" + id.ToString());
                responseTask.Wait();

                var result = responseTask.Result;
                if (result.IsSuccessStatusCode)
                {
                    var readTask = result.Content.ReadAsAsync<Orders>();
                    readTask.Wait();

                    orders = readTask.Result;
                }
            }
            return View(orders);
        }

        //affect order to basket
        [HttpPost]
        public ActionResult Affect(Orders order, int id, Basket basket)
        {
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri("http://localhost:8080/");
                var putTask = client.PutAsJsonAsync<Orders>("affectOrderToBasket/" + basket.id_basket.ToString() + "/" + id.ToString(), order);

                putTask.Wait();

                var ressult = putTask.Result;
                if (ressult.IsSuccessStatusCode)

                    return RedirectToAction("Index");
                return View(order);

            }
        }

        public ActionResult removefrombasket(int id)
        {
            Basket basket = null;

            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri("http://localhost:8080/");
                var responseTask = client.GetAsync("retrieve-basket/" + id.ToString());
                responseTask.Wait();

                var result = responseTask.Result;
                if (result.IsSuccessStatusCode)
                {
                    var readTask = result.Content.ReadAsAsync<Basket>();
                    readTask.Wait();

                    basket = readTask.Result;
                }
            }
            return View(basket);
        }

        [HttpPost]
        public ActionResult removefrombasket(Orders order, int id, Basket basket)
        {
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri("http://localhost:8080/");
                var putTask = client.PutAsJsonAsync<Orders>("removeOrderfromBasket/" + basket.id_basket.ToString() + "/" + id.ToString(), order);

                putTask.Wait();

                var ressult = putTask.Result;
                if (ressult.IsSuccessStatusCode)

                    return RedirectToAction("Index");
                return View(order);

            }
        }


    }
}
