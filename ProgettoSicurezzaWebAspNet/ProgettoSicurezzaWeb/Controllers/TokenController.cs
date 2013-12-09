using Microsoft.AspNet.Identity;
using Microsoft.AspNet.Identity.EntityFramework;
using Newtonsoft.Json;
using ProgettoSicurezzaWeb.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace ProgettoSicurezzaWeb.Controllers
{
    
    public class TokenController : ApiController
    {
        public UserManager<ApplicationUser> UserManager { get; private set; }
        public TokenController()
            : this(new UserManager<ApplicationUser>(new UserStore<ApplicationUser>(new ApplicationDbContext())))
        {
        }

        public TokenController(UserManager<ApplicationUser> userManager)
        {
            UserManager = userManager;
        }

        public string Get(string username, string password)
        {
            //validazione utente
            var user = UserManager.Find(username, password);
            if (user == null)
                return null;
            //creazione oggetto Token

            AuthenticationToken authToken = new AuthenticationToken
            {
                Token = Guid.NewGuid(),
                Username = username,
                CreationDate = DateTime.Now
            };
            string token = JsonConvert.SerializeObject(authToken);

            //criptazione Token
            string result = Crypto.EncryptStringAES(token, "AppLeoTomLuca");

            return result;
        }

        public string Options()
        {
            return "";
        }
    }
}
