package co.iyubinest.payuchallenge.presentation.movement;

import java.util.List;

import co.iyubinest.payuchallenge.domain.movement.Movement;

public interface MovementListView {

	void load(List<Movement> movements);

	void updateList();

}
