using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace pi2.Models
{
    public class Orders
    {

        public int id_order { get; set; }

        public String status_order { get; set; }

        public float fees_order { get; set; }

        public int discount { get; set; }

        public DateTime date_order { get; set; }
        
        public Basket basket { get; set; }

        public List<Product> product { get; set; }

        public Product products { get; set; }
    }
}
