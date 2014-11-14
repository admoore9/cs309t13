package edu.iastate.utils;

import edu.iastate.models.Member;
import edu.iastate.models.Member.UserType;

public class MemberUtils {

    /**
     * Returns true if the given member is an admin or a coordinator.
     * 
     * @param member The member to check.
     * @return true if the given member is an admin or coordinator, false
     *         otherwise.
     */
    public static boolean atLeastCoordinator(Member member) {
        UserType userType = member.getUserType();
        return userType == Member.UserType.ADMIN || userType == Member.UserType.COORDINATOR;
    }

    /**
     * Returns true if the given member is an admin, coordinator, or official.
     * 
     * @param member The member to check.
     * @return true if the given member is an admin, coordinator, or official,
     *         false otherwise.
     */
    public static boolean atLeastOfficial(Member member) {
        UserType userType = member.getUserType();
        return atLeastCoordinator(member) || userType == Member.UserType.OFFICIAL;
    }
}
