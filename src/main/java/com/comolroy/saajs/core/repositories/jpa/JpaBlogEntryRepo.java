package com.comolroy.saajs.core.repositories.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.comolroy.saajs.core.entities.BlogEntry;
import com.comolroy.saajs.core.repositories.BlogEntryRepo;

@Repository
public class JpaBlogEntryRepo implements BlogEntryRepo {

	@PersistenceContext
	private EntityManager em;

	@Override
	public BlogEntry findBlogEntry(Long id) {
		BlogEntry blogEntry = em.find(BlogEntry.class, id);
		return blogEntry;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BlogEntry> findByBlogId(Long blogId) {
		Query query = em.createQuery("SELECT b FROM BlogEntry b WHERE b.blog.id=?1");
		query.setParameter(1, blogId);
		return query.getResultList();
	}

	@Override
	public BlogEntry createBlogEntry(BlogEntry data) {
		em.persist(data);
		return data;
	}

	@Override
	public BlogEntry updateBlogEntry(Long id, BlogEntry data) {
		BlogEntry blogEntry = em.find(BlogEntry.class, id);
		blogEntry.setTitle(data.getTitle());
		blogEntry.setContent(data.getContent());
		return blogEntry;
	}

	@Override
	public BlogEntry deleteBlogEntry(Long id) {
		BlogEntry blogEntry = em.find(BlogEntry.class, id);
		em.remove(blogEntry);
		return blogEntry;
	}

}
