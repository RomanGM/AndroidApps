using DataLayer.Interfaces;
using Microsoft.EntityFrameworkCore;
using System;
using DataLayer;
using System.Collections.Generic;
using System.Linq;

namespace DataLayer.Repositories
{
    public class BaseRepository<T> : IRepository<T> where T : class
    {
        private readonly ApplicationDbContext context;
        private DbSet<T> dbSet;

        public BaseRepository( ApplicationDbContext context )
        {
            this.context = context;
            dbSet = context.Set<T>();
        }

        public T Get( int id )
        {
            return dbSet.Find( id );
        }

        public IEnumerable<T> GetAll()
        {
            return dbSet.AsEnumerable();
        }

        public void Create( T item )
        {
            if (item != null)
            {
                dbSet.Add( item );
                context.SaveChanges();
            }
        }

        public void Update( T item )
        {
            if (item != null)
            {
                dbSet.Attach( item );
                context.Entry( item ).State = EntityState.Modified;
            }
        }

        public void Delete( T item )
        {
            if (item != null)
            {
                dbSet.Remove( item );
                context.SaveChanges();
            }
        }

        public IEnumerable<T> Find( Func<T, bool> predicate )
        {
            return dbSet.Where( predicate ).ToList<T>();
        }
    }
}
