/*
 * This file is part of ComputerCraft - http://www.computercraft.info
 * Copyright Daniel Ratcliffe, 2011-2021. Do not distribute without permission.
 * Send enquiries to dratcliffe@gmail.com
 */

package dan200.computercraft.shared.network.client;

import net.fabricmc.fabric.api.network.PacketContext;
import net.minecraft.network.FriendlyByteBuf;

import javax.annotation.Nonnull;

public class ComputerTerminalClientMessage extends ComputerClientMessage
{
    private final TerminalState state;

    public ComputerTerminalClientMessage( int instanceId, TerminalState state )
    {
        super( instanceId );
        this.state = state;
    }

    public ComputerTerminalClientMessage( @Nonnull FriendlyByteBuf buf )
    {
        super( buf );
        state = new TerminalState( buf );
    }

    @Override
    public void toBytes( @Nonnull FriendlyByteBuf buf )
    {
        super.toBytes( buf );
        state.write( buf );
    }

    @Override
    public void handle( PacketContext context )
    {
        getComputer().read( state );
    }
}
