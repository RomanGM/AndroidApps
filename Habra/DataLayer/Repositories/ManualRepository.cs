using DataLayer.Interfaces;
using DataLayer.Models;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace DataLayer.Repositories
{
    public class ManualRepository : BaseRepository<Manual>, IManualRepository
    {
        protected readonly ApplicationDbContext context;
        private DbSet<Manual> dbSet;

        public ManualRepository( ApplicationDbContext context ) : base(context)
        {
            this.context = context;
            dbSet = context.Set<Manual>();
        }

        public List<Manual> GetUserPosts(ApplicationUser user)
        {
            return context.Manuals.Where( x => x.OwnerId == user.Id ).ToList();
        }

        public List<Manual> GetPostsByTime( DateTime date )
        {
            return context.Manuals.Where( x => x.Date == date ).ToList();
        }
    }
}
