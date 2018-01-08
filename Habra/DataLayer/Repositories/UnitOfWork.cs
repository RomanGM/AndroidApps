using DataLayer.Interfaces;

namespace DataLayer.Repositories
{
    public class UnitOfWork : IUnitOfWork
    {
        private ApplicationDbContext dbContext;

        public UnitOfWork( ApplicationDbContext context )
        {
            this.dbContext = context;
        }

        public void Commit()
        {
            dbContext.SaveChanges();
        }
    }
}
