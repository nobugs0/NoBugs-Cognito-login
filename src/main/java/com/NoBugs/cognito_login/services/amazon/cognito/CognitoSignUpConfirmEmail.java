package com.clubhive.organizers.services.amazon.cognito;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.AttributeType;
import com.amazonaws.services.cognitoidp.model.SignUpRequest;
import com.amazonaws.services.cognitoidp.model.SignUpResult;
import exceptions.NoBugsException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CognitoSignUpConfirmEmail extends CognitoService{

    public CognitoSignUpConfirmEmail(AWSCognitoIdentityProvider cognitoClient) {
        super(cognitoClient);
    }

    @Override
    public SignUpResult signUp(String username, String password, List<AttributeType> attributes) {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setClientId(super.getClientId());
        signUpRequest.setUsername(username);
        signUpRequest.setPassword(password);
        signUpRequest.setUserAttributes(attributes);

        SignUpResult signUpResult;

        try{
            signUpResult = getCognitoClient().signUp(signUpRequest);
        } catch (Exception e) {
            throw new NoBugsException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return signUpResult;
    }
}