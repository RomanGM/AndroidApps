using DataLayer.Models;
using Microsoft.AspNet.Identity;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using System;
using System.Linq;

namespace DataLayer
{
    public static class DbInitializer
    {
        public static void Initialize( ApplicationDbContext context )
        {
            //context.Database.EnsureDeleted();
            if (context.Database.EnsureCreated())
            {
                Picture pic = new Picture()
                {
                    Path = "/images/art/post1.jpg"
                };
                ApplicationUser user = CreateAdmin( context );
                context.Pictures.Add( pic );
                for (int i = 0; i < 10; i++)
                {
                    context.Manuals.Add( new Manual()
                    {
                        Content = "",
                        Date = DateTime.Now.AddDays(-i),
                        Picture = pic,
                        Owner = user,
                        Name = "Morning Glory",
                        Synopsis = "A wonderful serenity has taken possession of my entire soul, like these sweet mornings of autumn which I enjoy with my whole heart."
                    } );
                }

                context.SaveChanges();
            }
        }

        public static ApplicationUser CreateAdmin( ApplicationDbContext context )
        {
            context.AddRange(  new IdentityRole { Name = "Admin" } );
            context.SaveChanges();
            IdentityRole admin = context.Roles.First( x => x.Name == "Admin" );

            string email = "xxx@yyy.com";
            string passwordHash = new PasswordHasher().HashPassword("123@Grm");
            context.Users.Add( new ApplicationUser() { Email = email, PasswordHash = passwordHash, EmailConfirmed = true } );
            context.SaveChanges();

            ApplicationUser user = context.Users.First( x => x.Email == email );
            context.UserRoles.Add( new IdentityUserRole<string>() { RoleId = admin.Id, UserId = user.Id} );
            context.SaveChanges();
            return user;
        }
    }
}
