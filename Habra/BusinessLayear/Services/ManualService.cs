using BusinessLayer.Interfaces;
using DataLayer.Interfaces;
using DataLayer.Models;
using System;
using System.Collections.Generic;

namespace BusinessLayer.Services
{
    public class ManualService : IManualService
    {
        private readonly IManualRepository manualRepository;
        private readonly IUnitOfWork unitOfWork;

        public ManualService( IManualRepository manualRepository, IUnitOfWork unitOfWork )
        {
            this.manualRepository = manualRepository;
            this.unitOfWork = unitOfWork;
        }

        public void Commit()
        {
            unitOfWork.Commit();
        }

        public void Create( Manual item )
        {
            manualRepository.Create( item );
        }

        public void Delete( Manual item )
        {
            manualRepository.Delete( item );
        }

        public Manual Get( int id )
        {
            return manualRepository.Get( id );
        }

        public IEnumerable<Manual> GetAll()
        {
            return manualRepository.GetAll();
        }

        public List<Manual> GetUserPosts( ApplicationUser user )
        {
            return manualRepository.GetUserPosts( user );
        }

        public List<Manual> GetPostsByTime( DateTime date )
        {
            return manualRepository.GetPostsByTime( date );
        }

        public void Update( Manual item )
        {
            manualRepository.Update( item );
        }
    }
}
