import org.apache.thrift.TException;


public class WineMateUserLoginProcessor implements WineMateServices.Iface{

	@Override
	public LoginResult login(User user) throws TException {
				
		System.out.println("received user login: " + user.userName);
		LoginResult loginResult = new LoginResult();
		
		loginResult.userId = 123456;
		loginResult.status = LoginStatus.LOGIN_SUCCESS;
		return loginResult;
	}

	@Override
	public RegistrationStatus registration(User user) throws TException {
		System.out.println("received user registration: " + user.userName);
		return RegistrationStatus.REGISTRATION_SUCCESS;
	}

	@Override
	public FindPasswordStatus findPassword(User user) throws TException {
		System.out.println("received user findPassword: " + user.userName);
		return FindPasswordStatus.PW_SUCCESS;
	}

	@Override
	public WineInfo authentication(TagInfo tagInfo) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean openBottle(BottleOpenInfo bottleOpenInfo) throws TException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public WineBasicInfoResponse getBasicInfo(
			WineBasicInfoRequest wineBasicInfoRequest) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WineReviewAndRatingReadResponse getWineReviewAndRating(
			WineReviewAndRatingReadRequest wineReviewAndRatingReadRequest)
			throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WineRatingWriteResponse writeWineRating(
			WineRatingWriteRequest wineRatingWriteRequest) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WineReviewWriteResponse writeWineReview(
			WineReviewWriteRequest wineReviewWriteRequest) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyRateRecordResponse getMyRateRecord(
			MyRateRecordRequest myRateRecordRequest) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

}
