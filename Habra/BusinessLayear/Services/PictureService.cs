using BusinessLayer.Interfaces;
using DataLayer.Interfaces;
using DataLayer.Models;
using System.Collections.Generic;

namespace BusinessLayer.Services
{
    public class PictureService : IPictureService
    {
        private readonly IRepository<Picture> pictureService;
        private readonly IUnitOfWork unitOfWork;

        public PictureService( IRepository<Picture> pictureService, IUnitOfWork unitOfWork )
        {
            this.pictureService = pictureService;
            this.unitOfWork = unitOfWork;
        }

        public void Commit()
        {
            unitOfWork.Commit();
        }

        public void Create( Picture item )
        {
            pictureService.Create( item );
        }

        public void Delete( Picture item )
        {
            pictureService.Create( item );
        }

        public Picture Get( int id )
        {
            return pictureService.Get( id );
        }

        public IEnumerable<Picture> GetAll()
        {
            return pictureService.GetAll();
        }

        public void Update( Picture item )
        {
            pictureService.Update( item );
        }
    }
}
