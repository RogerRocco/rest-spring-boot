package roger.training.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class BankslipDao {

	@PersistenceContext
	private EntityManager entityManager;

	public Bankslip find(String id) {
		return entityManager.find(Bankslip.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Bankslip> getList() {
		return entityManager.createQuery("select p from Bankslip p").getResultList();
	}

	@Transactional
	public Bankslip save(Bankslip bankslipk) {
		if (bankslipk.getId() == null) {
			bankslipk.setId(UUID.randomUUID().toString());
			entityManager.persist(bankslipk);
			return bankslipk;
		} else {
			return entityManager.merge(bankslipk);
		}
	}

}
