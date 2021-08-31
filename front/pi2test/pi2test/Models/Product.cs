using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace pi2.Models
{
    public class Product
    {
        public int id_prod { get; set; }      

        public int quantity { get; set; }

        public String name_prod { get; set; }

        public String description_prod { get; set; }

        public int barcode_prod { get; set; }

        public float price_prod { get; set; }

        public String weight_prod { get; set; }

        public int minvalue_stock { get; set; }

        
        public Category category { get; set; }

        public List<Orders> orders { get; set; }
    }
}
