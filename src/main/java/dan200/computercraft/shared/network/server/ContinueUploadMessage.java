/*
 * This file is part of ComputerCraft - http://www.computercraft.info
 * Copyright Daniel Ratcliffe, 2011-2021. Do not distribute without permission.
 * Send enquiries to dratcliffe@gmail.com
 */
package dan200.computercraft.shared.network.server;

import dan200.computercraft.shared.computer.core.IContainerComputer;
import dan200.computercraft.shared.computer.core.ServerComputer;
import net.fabricmc.fabric.api.network.PacketContext;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;

import javax.annotation.Nonnull;

public class ContinueUploadMessage extends ComputerServerMessage
{
    private final boolean overwrite;

    public ContinueUploadMessage( int instanceId, boolean overwrite )
    {
        super( instanceId );
        this.overwrite = overwrite;
    }

    public ContinueUploadMessage( @Nonnull FriendlyByteBuf buf )
    {
        super( buf );
        overwrite = buf.readBoolean();
    }

    @Override
    public void toBytes( @Nonnull FriendlyByteBuf buf )
    {
        super.toBytes( buf );
        buf.writeBoolean( overwrite );
    }

    @Override
    protected void handle( PacketContext context, @Nonnull ServerComputer computer, @Nonnull IContainerComputer container )
    {
        ServerPlayer player = (ServerPlayer) context.getPlayer();
        if( player != null ) container.confirmUpload( player, overwrite );
    }
}
