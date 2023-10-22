package tp3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

public class NemoTests {

    private static final String FAIL_MESSAGE = "Nemo should throw an error when trying to go up from the surface";

    @Test
    public void test01NewNemoIsOnSurface() {
        Nemo nemo = startNemo00North();
        assertTrue( nemo.isOnSurface() );
    }

    @Test
    public void test02NoMovementWhenNoOrderGiven(){
        Nemo nemo = startNemo00North();
        nemo.move(' ');
        checkPAndO00North(nemo);
    }

    @Test
    public void test03NemoInitiatorWorksWithNegatives(){
        Nemo nemo = startNemoNeg519East();
        checkPandOneg519East(nemo);
    }

    @Test
    public void test04NemoDives(){
        Nemo nemo = startNemo00North();
        nemo.move('d');
        checkPositionAndOrientation(nemo, 0, 0, 1, "North");
    }

    @Test
    public void test05NemoCantGoUpIfOnSurface() {
        Nemo nemo = startNemo00North();
        nemo.move('u');
        checkPAndO00North(nemo);
    }

    @Test
    public void test06NemoNorthTurnsRightEast(){
        Nemo nemo = startNemo00North();
        nemo.move('r');
        checkPandO00East(nemo);
    }

    @Test
    public void test07NemoEastTurnsRightSouth(){
        Nemo nemo = startNemo00East();
        nemo.move('r');
        checkPandO00South(nemo);
    }

    @Test
    public void test08NemoSouthTurnsRightWest(){
        Nemo nemo = startNemo00South();
        nemo.move('r');
        checkPandO00West(nemo);
    }

    @Test
    public void test09NemoWestTurnsRightNorth(){
        Nemo nemo = startNemo00West();
        nemo.move('r');
        checkPAndO00North(nemo);
    }

    @Test
    public void test10NemoNorthTurnsLeftWest(){
        Nemo nemo = startNemo00North();
        nemo.move('l');
        checkPandO00West(nemo);
    }

    @Test
    public void test11NemoEastTurnsLeftNorth(){
        Nemo nemo = startNemo00East();
        nemo.move('l');
        checkPAndO00North(nemo);
    }

    @Test
    public void test12NemoSouthTurnsLeftEast(){
        Nemo nemo = startNemo00South();
        nemo.move('l');
        checkPandO00East(nemo);
    }

    @Test
    public void test13NemoWestTurnsLeftSouth(){
        Nemo nemo = startNemo00West();
        nemo.move('l');
        checkPandO00South(nemo);
    }

    @Test
    public void test14NemoFacingNorthMovesForward(){
        Nemo nemo = startNemo00North();
        nemo.move('f');
        checkPositionAndOrientation(nemo, 0, 1, 0, "North");
    }

    @Test
    public void test15NemoFacingEastMovesForward(){
        Nemo nemo = startNemo00East();
        nemo.move('f');
        checkPositionAndOrientation(nemo, 1, 0, 0, "East");
    }

    @Test
    public void test16NemoFacingSouthMovesForward(){
        Nemo nemo = startNemo00South();
        nemo.move('f');
        checkPositionAndOrientation(nemo, 0, -1, 0, "South");
    }

    @Test
    public void test17NemoFacingWestMovesForward(){
        Nemo nemo = startNemo00West();
        nemo.move('f');
        checkPositionAndOrientation(nemo, -1, 0, 0, "West");
    }

    @Test
    public void test18WhenNemoReleasesMissileNothingChanges(){
        Nemo nemo = startNemoNeg519East();
        nemo.move('m');
        checkPandOneg519East(nemo);
    }

    @Test
    public void test19NemoDoesntChangeIfReceivingWeirdInstruction(){
        Nemo nemo = startNemoNeg519East();
        nemo.move('j');
        checkPandOneg519East(nemo);
    }

    @Test
    public void test20NemoCanReceiveEmptyString(){
        Nemo nemo = startNemo00North();
        nemo.move("");
        checkPAndO00North(nemo);
    }

    @Test
    public void test21NemoCanReleaseMissileInShallowWater(){
        Nemo nemo = startNemo00North();
        nemo.move("dm");
        checkPositionAndOrientation(nemo, 0, 0, 1, "North");
    }
    
    @Test
    public void test22NemoCantReleaseMissileBelowShallow(){
        Nemo nemo = startNemo00North();
        nemo.move("dd");
        assertThrowsLike( () -> nemo.move('m') );
    }

    @Test
    public void test23NemoCanExecuteSeveralInstructions(){
        Nemo nemo = startNemo00North();
        nemo.move("dmdduummffmrrffjyxzlffmdddd");
        checkPositionAndOrientation(nemo, 2, 0, 5, "East");
    }

    private void assertThrowsLike( Executable executable ) {
        assertEquals(Bottom.ERRORCAPSULE,
                assertThrows(RuntimeException.class, executable, FAIL_MESSAGE)
                        .getMessage());
    }

    private Nemo startNemo00North() {
        return new Nemo( 0, 0, new North() );
    }

    private Nemo startNemoNeg519East() {
        return new Nemo( -5, 19, new East() );
    }

    private Nemo startNemo00East() {
        Nemo nemoE = new Nemo( 0, 0, new East() );
        return nemoE;
    }

    private Nemo startNemo00West() {
        Nemo nemoW = new Nemo( 0, 0, new West() );
        return nemoW;
    }

    private Nemo startNemo00South() {
        Nemo nemoS = new Nemo( 0, 0, new South() );
        return nemoS;
    }

    private void checkPositionAndOrientation(Nemo nemo, int x, int y, int z, String orientation) {
        assertArrayEquals( new int[]{x, y, z}, nemo.getPosition() );
        assertEquals( orientation, nemo.getOrientation() );
    }

    private void checkPAndO00North(Nemo nemo) {
        checkPositionAndOrientation(nemo, 0, 0, 0, "North");
    }

    private void checkPandOneg519East(Nemo nemo) {
        checkPositionAndOrientation(nemo, -5, 19, 0, "East");
    }

    private void checkPandO00East(Nemo nemo) {
        checkPositionAndOrientation(nemo, 0, 0, 0, "East");
    }

    private void checkPandO00South(Nemo nemo) {
        checkPositionAndOrientation(nemo, 0, 0, 0, "South");
    }

    private void checkPandO00West(Nemo nemo) {
        checkPositionAndOrientation(nemo, 0, 0, 0, "West");
    }
}
