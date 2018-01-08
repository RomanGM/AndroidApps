using BusinessLayer.Interfaces;
using DataLayer.Models;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using System.Linq;
using System.Threading.Tasks;

namespace Habra2.Controllers
{
    [Authorize]
    public class WallController : Controller
    {
        private readonly IManualService manualService;
        private readonly IPictureService pictureService;
        private readonly ICommentService commentService;
        private readonly UserManager<ApplicationUser> userManager;

        public WallController( IManualService manualService,
            IPictureService pictureService,
            ICommentService commentService,
            UserManager<ApplicationUser> userManager
            )
        {
            this.userManager = userManager;
            this.commentService = commentService;
            this.manualService = manualService;
            this.pictureService = pictureService;
        }

        public IActionResult ShowPosts()
        {
            var manuals = manualService.GetAll();
            foreach (var manual in manuals)
            {
                manual.Picture = pictureService.Get( manual.PictureId );
            }
            return View( manuals );
        }

        [Authorize]
        public IActionResult ShowPost( int id )
        {
            var manual = manualService.Get( id );
            manual.Picture = pictureService.Get( manual.PictureId );
            manual.Comments = commentService.GetAll().Where( x => x.ManualId == manual.ManualId ).ToList();
            return View( manual );
        }

        [HttpGet]
        public async Task<IActionResult> MyPosts()
        {
            var user = await userManager.GetUserAsync( User );
            var manuals = manualService.GetUserPosts( user );
            foreach (var manual in manuals)
            {
                manual.Picture = pictureService.Get( manual.PictureId );
            }
            return View( manuals );
        }

        public IActionResult DeletePost( int id )
        {
            Manual manual = manualService.Get( id );
            manualService.Delete( manual );
            return RedirectToAction( "MyPosts" );
        }

        public async Task<IActionResult> AddComment( int id, string content )
        {
            var user = await userManager.GetUserAsync( User );
            Comment comment = new Comment() { owner = user, Content = content, ManualId = id };
            commentService.Create( comment );
            return RedirectToAction( "ShowPost", id );
        }
    }
}