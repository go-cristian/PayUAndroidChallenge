package co.iyubinest.payuchallenge.presentation.account;

import java.util.List;

import co.iyubinest.payuchallenge.domain.movement.Movement;

public interface AccountInfoView {

	void load(List<Movement> movements);

}
