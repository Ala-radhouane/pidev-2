using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace pi2.Models
{
    public class Basket
    {

        public int id_basket { get; set; }

        public DateTime date_basket { get; set; }

        public float total { get; set; }

        public String type_paiement { get; set; }
        public Client client { get; set; }
        public List<Orders> orders { get; set; }
        public Product product { get; set; }

        
    }
}
