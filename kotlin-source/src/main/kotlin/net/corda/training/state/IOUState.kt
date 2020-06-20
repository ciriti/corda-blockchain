package net.corda.training.state

import net.corda.core.contracts.*
import net.corda.core.identity.Party
import net.corda.training.contract.IOUContract
import java.util.*

/**
 * This is where you'll add the definition of your state object. Look at the unit tests in [IOUStateTests] for
 * instructions on how to complete the [IOUState] class.
 *
 * Remove the "val data: String = "data" property before starting the [IOUState] tasks.
 */
@BelongsToContract(IOUContract::class)
data class IOUState(
        val amount: Amount<Currency>,
        val lender: Party,
        val borrower: Party,
        val paid: Amount<Currency> = Amount.zero(amount.token),
        override val linearId : UniqueIdentifier = UniqueIdentifier()
): ContractState, LinearState {

    fun pay(dollars: Amount<Currency>): IOUState = this.copy(paid = this.paid + dollars)

    fun withNewLender(party: Party): IOUState = this.copy(lender = party)

    override val participants: List<Party> get() = listOf(lender, borrower)
}