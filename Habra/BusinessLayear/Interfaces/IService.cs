using System.Collections.Generic;

namespace BusinessLayer.Interfaces
{
    public interface IService<T>
    {
        void Create( T item );
        void Delete( T item );
        void Update( T item );
        T Get( int id );
        IEnumerable<T> GetAll();
        void Commit();
    }
}
