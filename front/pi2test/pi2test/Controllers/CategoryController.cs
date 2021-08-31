
using pi2.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web.Mvc;

namespace pi2.Controllers
{
    public class CategoryController : Controller
    {
        // GET: CategoryController
        public ActionResult Index()
        {
            IEnumerable<Category> category = null;
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri("http://localhost:8080/");
                var responseTask = client.GetAsync("retrieve-all-categorys");
                responseTask.Wait();

                var result = responseTask.Result;
                if (result.IsSuccessStatusCode)
                {
                    var readJob = result.Content.ReadAsAsync<IList<Category>>();
                    readJob.Wait();
                    category = readJob.Result;

                }
                else
                {
                    category = Enumerable.Empty<Category>();
                }
            }
            return View(category);
        }

        // GET: CategoryController/Details/5
        public ActionResult Details(int id)
        {
            Category categories = null;

            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri("http://localhost:8080/");
                var responseTask = client.GetAsync("retrieve-category/" + id.ToString());
                responseTask.Wait();

                var result = responseTask.Result;
                if (result.IsSuccessStatusCode)
                {
                    var readTask = result.Content.ReadAsAsync<Category>();
                    readTask.Wait();

                    categories = readTask.Result;
                }
            }
            return View(categories);
        }
    

        // GET: CategoryController/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: CategoryController/Create
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create(Category category)
        {
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri("http://localhost:8080/");
                var postJob = client.PostAsJsonAsync<Category>("add-category", category);
                postJob.Wait();

                var postResult = postJob.Result;
                if (postResult.IsSuccessStatusCode)
                {
                    return RedirectToAction("Index");
                }
                return View(category);
            }
        }

        

        // GET: CategoryController/Delete/5
        public ActionResult Delete(int id)
        {
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri("http://localhost:8080/");
                var deleteTask = client.DeleteAsync("remove-category/" + id.ToString());
                Console.WriteLine(id);
                var result = deleteTask.Result;
                if (result.IsSuccessStatusCode)
                {
                    return Redirect("/Category/Index");
                }
                return Redirect("/Category/Index");
            }
        }

        
    }
}
