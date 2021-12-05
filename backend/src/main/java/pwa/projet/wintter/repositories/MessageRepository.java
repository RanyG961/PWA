package pwa.projet.wintter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pwa.projet.wintter.models.Message;

public interface MessageRepository extends JpaRepository<Message, Long>
{
}
