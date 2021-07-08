// sound has not been ported yet
internal class Sound(d: Digger) {
    var dig: Digger
    var wavetype = 0
    var t2val = 0
    var t0val = 0
    var musvol = 0
    var spkrmode = 0
    var timerrate = 0x7d0
    var timercount = 0
    var pulsewidth = 1
    var volume = 0
    var timerclock = 0 // sint3
    var soundflag = true
    var musicflag = true
    var sndflag = false
    var soundpausedflag = false
    var soundlevdoneflag = false
    var nljpointer = 0
    var nljnoteduration = 0
    var newlevjingle = intArrayOf(0x8e8, 0x712, 0x5f2, 0x7f0, 0x6ac, 0x54c, 0x712, 0x5f2, 0x4b8, 0x474, 0x474) // [11]
    var soundfallflag = false
    var soundfallf = false
    var soundfallvalue = 0
    var soundfalln = 0
    var soundbreakflag = false
    var soundbreakduration = 0
    var soundbreakvalue = 0
    var soundwobbleflag = false
    var soundwobblen = 0
    var soundfireflag = false
    var soundfirevalue = 0
    var soundfiren = 0
    var soundexplodeflag = false
    var soundexplodevalue = 0
    var soundexplodeduration = 0
    var soundbonusflag = false
    var soundbonusn = 0
    var soundemflag = false
    var soundemeraldflag = false
    var soundemeraldduration = 0
    var emerfreq = 0
    var soundemeraldn = 0
    var soundgoldflag = false
    var soundgoldf = false
    var soundgoldvalue1 = 0
    var soundgoldvalue2 = 0
    var soundgoldduration = 0
    var soundeatmflag = false
    var soundeatmvalue = 0
    var soundeatmduration = 0
    var soundeatmn = 0
    var soundddieflag = false
    var soundddien = 0
    var soundddievalue = 0
    var sound1upflag = false
    var sound1upduration = 0
    var musicplaying = false
    var musicp = 0
    var tuneno = 0
    var noteduration = 0
    var notevalue = 0
    var musicmaxvol = 0
    var musicattackrate = 0
    var musicsustainlevel = 0
    var musicdecayrate = 0
    var musicnotewidth = 0
    var musicreleaserate = 0
    var musicstage = 0
    var musicn = 0
    var soundt0flag = false
    var int8flag = false
    fun initsound() {
        wavetype = 2
        t0val = 12000
        musvol = 8
        t2val = 40
        soundt0flag = true
        sndflag = true
        spkrmode = 0
        int8flag = false
        setsoundt2()
        soundstop()
        startint8()
        timerrate = 0x4000
    }

    fun killsound() {}
    fun music(tune: Int) {
        tuneno = tune
        musicp = 0
        noteduration = 0
        when (tune) {
            0 -> {
                musicmaxvol = 50
                musicattackrate = 20
                musicsustainlevel = 20
                musicdecayrate = 10
                musicreleaserate = 4
            }
            1 -> {
                musicmaxvol = 50
                musicattackrate = 50
                musicsustainlevel = 8
                musicdecayrate = 15
                musicreleaserate = 1
            }
            2 -> {
                musicmaxvol = 50
                musicattackrate = 50
                musicsustainlevel = 25
                musicdecayrate = 5
                musicreleaserate = 1
            }
        }
        musicplaying = true
        if (tune == 2) soundddieoff()
    }

    fun musicoff() {
        musicplaying = false
        musicp = 0
    }

    fun musicupdate() {
        if (!musicplaying) return
        if (noteduration != 0) noteduration-- else {
            musicn = 0
            musicstage = musicn
            when (tuneno) {
                0 -> {
                    musicnotewidth = noteduration - 3
                    musicp += 2
                }
                1 -> {
                    musicnotewidth = 12
                    musicp += 2
                }
                2 -> {
                    musicnotewidth = noteduration - 10
                    musicp += 2
                }
            }
        }
        musicn++
        wavetype = 1
        t0val = notevalue
        if (musicn >= musicnotewidth) musicstage = 2
        when (musicstage) {
            0 -> {
                if (musvol + musicattackrate >= musicmaxvol) {
                    musicstage = 1
                    musvol = musicmaxvol
                    return // break
                }
                musvol += musicattackrate
            }
            1 -> {
                if (musvol - musicdecayrate <= musicsustainlevel) {
                    musvol = musicsustainlevel
                    return // break
                }
                musvol -= musicdecayrate
            }
            2 -> {
                if (musvol - musicreleaserate <= 1) {
                    musvol = 1
                    return // break
                }
                musvol -= musicreleaserate
            }
        }
        if (musvol == 1) t0val = 0x7d00
    }

    fun s0fillbuffer() {}
    fun s0killsound() {
        setsoundt2()
        stopint8()
    }

    fun s0setupsound() {
        startint8()
    }

    fun setsoundmode() {
        spkrmode = wavetype
        if (!soundt0flag && sndflag) {
            soundt0flag = true
        }
    }

    fun setsoundt2() {
        if (soundt0flag) {
            spkrmode = 0
            soundt0flag = false
        }
    }

    fun sett0() {
        if (sndflag) {
            if (t0val < 1000 && (wavetype == 1 || wavetype == 2)) t0val = 1000
            timerrate = t0val
            if (musvol < 1) musvol = 1
            if (musvol > 50) musvol = 50
            pulsewidth = musvol * volume
            setsoundmode()
        }
    }

    fun sett2val(t2v: Int) {}
    fun setupsound() {}
    fun sound1up() {
        sound1upduration = 96
        sound1upflag = true
    }

    fun sound1upoff() {
        sound1upflag = false
    }

    fun sound1upupdate() {
        if (sound1upflag) {
            if (sound1upduration / 3 % 2 != 0) t2val = (sound1upduration shl 2) + 600
            sound1upduration--
            if (sound1upduration < 1) sound1upflag = false
        }
    }

    fun soundbonus() {
        soundbonusflag = true
    }

    fun soundbonusoff() {
        soundbonusflag = false
        soundbonusn = 0
    }

    fun soundbonusupdate() {
        if (soundbonusflag) {
            soundbonusn++
            if (soundbonusn > 15) soundbonusn = 0
            if (soundbonusn >= 0 && soundbonusn < 6) t2val = 0x4ce
            if (soundbonusn >= 8 && soundbonusn < 14) t2val = 0x5e9
        }
    }

    fun soundbreak() {
        soundbreakduration = 3
        if (soundbreakvalue < 15000) soundbreakvalue = 15000
        soundbreakflag = true
    }

    fun soundbreakoff() {
        soundbreakflag = false
    }

    fun soundbreakupdate() {
        if (soundbreakflag) if (soundbreakduration != 0) {
            soundbreakduration--
            t2val = soundbreakvalue
        } else soundbreakflag = false
    }

    fun soundddie() {
        soundddien = 0
        soundddievalue = 20000
        soundddieflag = true
    }

    fun soundddieoff() {
        soundddieflag = false
    }

    fun soundddieupdate() {
        if (soundddieflag) {
            soundddien++
            if (soundddien == 1) musicoff()
            if (soundddien >= 1 && soundddien <= 10) soundddievalue = 20000 - soundddien * 1000
            if (soundddien > 10) soundddievalue += 500
            if (soundddievalue > 30000) soundddieoff()
            t2val = soundddievalue
        }
    }

    fun soundeatm() {
        soundeatmduration = 20
        soundeatmn = 3
        soundeatmvalue = 2000
        soundeatmflag = true
    }

    fun soundeatmoff() {
        soundeatmflag = false
    }

    fun soundeatmupdate() {
        if (soundeatmflag) if (soundeatmn != 0) {
            if (soundeatmduration != 0) {
                if (soundeatmduration % 4 == 1) t2val = soundeatmvalue
                if (soundeatmduration % 4 == 3) t2val = soundeatmvalue - (soundeatmvalue shr 4)
                soundeatmduration--
                soundeatmvalue -= soundeatmvalue shr 4
            } else {
                soundeatmduration = 20
                soundeatmn--
                soundeatmvalue = 2000
            }
        } else soundeatmflag = false
    }

    fun soundem() {
        soundemflag = true
    }

    fun soundemerald(emocttime: Int) {
        if (emocttime != 0) {
            when (emerfreq) {
                0x8e8 -> emerfreq = 0x7f0
                0x7f0 -> emerfreq = 0x712
                0x712 -> emerfreq = 0x6ac
                0x6ac -> emerfreq = 0x5f2
                0x5f2 -> emerfreq = 0x54c
                0x54c -> emerfreq = 0x4b8
                0x4b8 -> {
                    emerfreq = 0x474
                    dig.Scores.scoreoctave()
                }
                0x474 -> emerfreq = 0x8e8
            }
        } else emerfreq = 0x8e8
        soundemeraldduration = 7
        soundemeraldn = 0
        soundemeraldflag = true
    }

    fun soundemeraldoff() {
        soundemeraldflag = false
    }

    fun soundemeraldupdate() {
        if (soundemeraldflag) if (soundemeraldduration != 0) {
            if (soundemeraldn == 0 || soundemeraldn == 1) t2val = emerfreq
            soundemeraldn++
            if (soundemeraldn > 7) {
                soundemeraldn = 0
                soundemeraldduration--
            }
        } else soundemeraldoff()
    }

    fun soundemoff() {
        soundemflag = false
    }

    fun soundemupdate() {
        if (soundemflag) {
            t2val = 1000
            soundemoff()
        }
    }

    fun soundexplode() {
        soundexplodevalue = 1500
        soundexplodeduration = 10
        soundexplodeflag = true
        soundfireoff()
    }

    fun soundexplodeoff() {
        soundexplodeflag = false
    }

    fun soundexplodeupdate() {
        if (soundexplodeflag) if (soundexplodeduration != 0) {
            t2val = soundexplodevalue - (soundexplodevalue shr 3)
            soundexplodevalue = t2val
            soundexplodeduration--
        } else soundexplodeflag = false
    }

    fun soundfall() {
        soundfallvalue = 1000
        soundfallflag = true
    }

    fun soundfalloff() {
        soundfallflag = false
        soundfalln = 0
    }

    fun soundfallupdate() {
        if (soundfallflag) if (soundfalln < 1) {
            soundfalln++
            if (soundfallf) t2val = soundfallvalue
        } else {
            soundfalln = 0
            if (soundfallf) {
                soundfallvalue += 50
                soundfallf = false
            } else soundfallf = true
        }
    }

    fun soundfire() {
        soundfirevalue = 500
        soundfireflag = true
    }

    fun soundfireoff() {
        soundfireflag = false
        soundfiren = 0
    }

    fun soundfireupdate() {
        if (soundfireflag) {
            if (soundfiren == 1) {
                soundfiren = 0
                soundfirevalue += soundfirevalue / 55
                t2val = soundfirevalue + dig.Main.randno(soundfirevalue shr 3)
                if (soundfirevalue > 30000) soundfireoff()
            } else soundfiren++
        }
    }

    fun soundgold() {
        soundgoldvalue1 = 500
        soundgoldvalue2 = 4000
        soundgoldduration = 30
        soundgoldf = false
        soundgoldflag = true
    }

    fun soundgoldoff() {
        soundgoldflag = false
    }

    fun soundgoldupdate() {
        if (soundgoldflag) {
            if (soundgoldduration != 0) soundgoldduration-- else soundgoldflag = false
            if (soundgoldf) {
                soundgoldf = false
                t2val = soundgoldvalue1
            } else {
                soundgoldf = true
                t2val = soundgoldvalue2
            }
            soundgoldvalue1 += soundgoldvalue1 shr 4
            soundgoldvalue2 -= soundgoldvalue2 shr 4
        }
    }

    fun soundint() {
        timerclock++
        if (soundflag && !sndflag) {
            musicflag = true
            sndflag = musicflag
        }
        if (!soundflag && sndflag) {
            sndflag = false
            setsoundt2()
        }
        if (sndflag && !soundpausedflag) {
            t0val = 0x7d00
            t2val = 40
            if (musicflag) musicupdate()
            soundemeraldupdate()
            soundwobbleupdate()
            soundddieupdate()
            soundbreakupdate()
            soundgoldupdate()
            soundemupdate()
            soundexplodeupdate()
            soundfireupdate()
            soundeatmupdate()
            soundfallupdate()
            sound1upupdate()
            soundbonusupdate()
            if (t0val == 0x7d00 || t2val != 40) setsoundt2() else {
                setsoundmode()
                sett0()
            }
            sett2val(t2val)
        }
    }

    fun soundlevdone() {
        try {
            Thread.sleep(1000)
        } catch (e: Exception) {
        }
    }

    fun soundlevdoneoff() {
        soundpausedflag = false
        soundlevdoneflag = soundpausedflag
    }

    fun soundlevdoneupdate() {
        if (sndflag) {
            if (nljpointer < 11) t2val = newlevjingle[nljpointer]
            t0val = t2val + 35
            musvol = 50
            setsoundmode()
            sett0()
            sett2val(t2val)
            if (nljnoteduration > 0) nljnoteduration-- else {
                nljnoteduration = 20
                nljpointer++
                if (nljpointer > 10) soundlevdoneoff()
            }
        } else {
            soundlevdoneflag = false
        }
    }

    fun soundoff() {}
    fun soundpause() {
        soundpausedflag = true
    }

    fun soundpauseoff() {
        soundpausedflag = false
    }

    fun soundstop() {
        soundfalloff()
        soundwobbleoff()
        soundfireoff()
        musicoff()
        soundbonusoff()
        soundexplodeoff()
        soundbreakoff()
        soundemoff()
        soundemeraldoff()
        soundgoldoff()
        soundeatmoff()
        soundddieoff()
        sound1upoff()
    }

    fun soundwobble() {
        soundwobbleflag = true
    }

    fun soundwobbleoff() {
        soundwobbleflag = false
        soundwobblen = 0
    }

    fun soundwobbleupdate() {
        if (soundwobbleflag) {
            soundwobblen++
            if (soundwobblen > 63) soundwobblen = 0
            when (soundwobblen) {
                0 -> t2val = 0x7d0
                16, 48 -> t2val = 0x9c4
                32 -> t2val = 0xbb8
            }
        }
    }

    fun startint8() {
        if (!int8flag) {
            timerrate = 0x4000
            int8flag = true
        }
    }

    fun stopint8() {
        if (int8flag) {
            int8flag = false
        }
        sett2val(40)
    }

    init {
        dig = d
    }
}