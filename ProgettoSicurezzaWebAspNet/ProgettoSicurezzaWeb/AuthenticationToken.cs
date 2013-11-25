using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ProgettoSicurezzaWeb
{
    public class AuthenticationToken
    {
        public Guid Token { get; set; }
        public DateTime CreationDate { get; set; }
        public string Username { get; set; }
    }
}