package com.craftaga.agamcmmowage.commands;

import com.craftaga.agabacbone.commands.Command;
import com.craftaga.agabacbone.commands.IValueHolderCommand;
import com.craftaga.agabacbone.commands.decision.IDecisionCommand;
import com.craftaga.agabacbone.commands.queue.CommandQueue;
import com.craftaga.agabacbone.commands.queue.SubQueue;

/**
 * description
 *
 * @author Jonathan
 * @since 11/04/14
 */
public class PaymentLargerThanZeroDecisionCommand extends Command implements IDecisionCommand {

    private IValueHolderCommand<Double> valueHolderCommand;
    private SubQueue overZero;
    private SubQueue zeroOrLess;

    public PaymentLargerThanZeroDecisionCommand(
            CommandQueue commandQueue,
            IValueHolderCommand<Double> valueHolderCommand,
            SubQueue overZero,
            SubQueue zeroOrLess
    ) {
        super(commandQueue);
        this.valueHolderCommand = valueHolderCommand;
        this.overZero = overZero;
        this.zeroOrLess = zeroOrLess;
    }

    @Override
    public void setSuccess(SubQueue overZero) {
        this.overZero = overZero;
    }

    @Override
    public SubQueue getSuccess() {
        return overZero;
    }

    @Override
    public void setFailure(SubQueue zeroOrLess) {
        this.zeroOrLess = zeroOrLess;
    }

    @Override
    public SubQueue getFailure() {
        return zeroOrLess;
    }

    @Override
    public void execute() {
        if (valueHolderCommand.getValue()!= null)
        {
            if (valueHolderCommand.getValue() > 0) {
                overZero.execute();
            } else {
                zeroOrLess.execute();
            }
        }
    }
}
