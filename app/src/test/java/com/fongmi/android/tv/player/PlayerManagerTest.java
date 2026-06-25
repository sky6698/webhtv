package com.fongmi.android.tv.player;

import static org.junit.Assert.assertEquals;

import com.fongmi.android.tv.player.engine.PlayerEngine;
import com.fongmi.android.tv.setting.PlayerSetting;

import org.junit.Test;

public class PlayerManagerTest {

    @Test
    public void fallbackDecode_resetsToHardWhenChangingPlayerCore() {
        assertEquals(PlayerEngine.HARD, PlayerManager.fallbackDecode(PlayerSetting.EXO, PlayerSetting.IJK, PlayerEngine.SOFT));
    }

    @Test
    public void fallbackDecode_keepsCurrentDecodeWhenCoreDoesNotChange() {
        assertEquals(PlayerEngine.SOFT, PlayerManager.fallbackDecode(PlayerSetting.EXO, PlayerSetting.EXO, PlayerEngine.SOFT));
    }

    @Test
    public void fallbackDecode_sanitizesUnknownDecodeToHard() {
        assertEquals(PlayerEngine.HARD, PlayerManager.fallbackDecode(PlayerSetting.EXO, PlayerSetting.EXO, 99));
    }
}
