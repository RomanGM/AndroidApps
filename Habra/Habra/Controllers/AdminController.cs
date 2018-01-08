using BusinessLayer.Interfaces;
using DataLayer;
using DataLayer.Models;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Linq;
using System.Threading.Tasks;

namespace Habra.Controllers
{
    [Authorize( Roles = "Admin" )]
    public class AdminController : Controller
    {
        private readonly UserManager<ApplicationUser> userManager;
        private readonly RoleManager<IdentityRole> roleManager;
        private readonly IManualService manualService;
        private readonly ApplicationDbContext context;

        public AdminController( 
            UserManager<ApplicationUser> userManager,
            RoleManager<IdentityRole> roleManager,
            IManualService manualService,
            ApplicationDbContext context)
        {
            this.context = context;
            this.userManager = userManager;
            this.roleManager = roleManager;
            this.manualService = manualService;
        }

        public IActionResult Users()
        {
            return View( userManager.Users );
        }

        public IActionResult Posts()
        {
            return View( manualService.GetAll() );
        }

        public IActionResult DeletePost(int id)
        {
            Manual manual = manualService.Get( id );
            manualService.Delete( manual );
            return RedirectToAction( "Posts" );
        }

        public async Task<IActionResult> DeleteUser( string id )
        {
            ApplicationUser user = await userManager.FindByIdAsync( id );
            await userManager.DeleteAsync( user );
            return RedirectToAction( "Users" );
        }

        public async Task<IActionResult> BlockUser(string id)
        {
            ApplicationUser user = await userManager.FindByIdAsync( id );
            await userManager.SetLockoutEnabledAsync( user, true );
            await userManager.SetLockoutEndDateAsync( user,  DateTime.Now.AddDays( 10 ) );
            return RedirectToAction( "Users" );
        }

        public async Task<IActionResult> CreateAdmin( string id )
        {
            ApplicationUser user = await userManager.FindByIdAsync( id );
            IdentityRole admin = context.Roles.First( x => x.Name == "Admin" );
            context.UserRoles.Add( new IdentityUserRole<string>() { RoleId = admin.Id, UserId = user.Id } );
            context.SaveChanges();
            return RedirectToAction( "Users" );
        }

        public async Task<IActionResult> GetUserPosts(string id)
        {
            ApplicationUser user = await userManager.FindByIdAsync( id );
            var posts = manualService.GetUserPosts(user);
            return View( "Posts", posts );
        }

        public IActionResult GetByTime( DateTime date )
        {
            var posts = manualService.GetPostsByTime( date );
            return View( "Posts", posts );
        }
    }
}