package lotto.domain;

public enum LottoRank {

    FIRST( 6, false, 2000000000),
    SECOND( 5, true, 30000000),
    THIRD(5, false, 1500000),
    FOURTH(4, false, 50000),
    FIFTH(3, false, 5000),;

    private final int requiredMatchCount;
    private final boolean bonusNumberMatch;
    private final long prize;

    LottoRank(int requiredMatchCount, boolean bonusNumberMatch, long prize) {
        this.requiredMatchCount = requiredMatchCount;
        this.bonusNumberMatch = bonusNumberMatch;
        this.prize = prize;
    }

    public int getRequiredMatchCount() {
        return requiredMatchCount;
    }

    public boolean requireBonusNumberMatch() {
        return bonusNumberMatch;
    }

    public long getPrize() {
        return prize;
    }
}
