using ProgettoSicurezzaWeb.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace ProgettoSicurezzaWeb.Controllers
{
    public class ProductController : ApiController
    {
        /// <summary>
        /// api/product
        /// </summary>
        /// <returns>lista prodotti</returns>
        public IEnumerable<Product> Get()
        {
            List<Product> prods = new List<Product>();
            prods.Add(new Product
            {
                Id = 1,
                Nome = "nome1",
                Descrizione = "desc1",
            });

            prods.Add(new Product
            {
                Id = 2,
                Nome = "nome2",
                Descrizione = "desc2",
            });

            prods.Add(new Product
            {
                Id = 3,
                Nome = "nome3",
                Descrizione = "desc3",
            });

            prods.Add(new Product
            {
                Id = 4,
                Nome = "nome4",
                Descrizione = "desc4",
            });

            prods.Add(new Product
            {
                Id = 5,
                Nome = "nome5",
                Descrizione = "desc5",
            });

            return prods;
        }

        /// <summary>
        /// api/product?id
        /// </summary>
        /// <param name="id">id del prodotto</param>
        /// <returns>prodotto</returns>
        public Product Get(int id)
        {
            return new Product{
                Id=id,
                Nome="nome"+id,
                Descrizione="desc"+id
            };
           
        }


    }
}
