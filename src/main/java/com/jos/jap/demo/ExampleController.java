package com.jos.jap.demo;

import org.springframework.core.io.ClassPathResource;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;

@RestController
public class ExampleController {
    private static String ssoAppPrivateKey="-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIICXAIBAAKBgQCta1d924GnlmrJbuo+swoaFcBBi6WiRpVLIYymwEDN0et6Dnpv\n" +
            "mGfQDqhQvA+PCT5NpohxIJJ7h2ZlWwlqBXEyxmISDDeIRtxRSksMRINvhJmig1A2\n" +
            "rUQPDj+R65yDCrOJqplKckXxVx+jzdfCGMhKngDV8kSyqqo72x7G9Z8GuQIDAQAB\n" +
            "AoGBAKpQEFXnfPcEj42SY3T+Sr6BfBYjOqEbZsWphkPB7iL0talijLYKXHIF4yGf\n" +
            "ADy+nDSQh9FqZtHwkQybkqxP+fGA15h4Lik1Ej8soDZav3SdvLXtR58FpHsmnPED\n" +
            "0xV/LHrEuB8jD/AMj1p0r0jfYmraWbbveYUvl5IeU291qgl9AkEA1vt3SHLoQG9Z\n" +
            "KxlhR89Fi5q3YdApJnAtErtHFDkoCqDQMcNU0kj+1QuqepKeWtsGCeZa6mn2ETNn\n" +
            "FLjX+EMCvwJBAM6ByP7SlZlsDrLEd6V/x5T5SaqtDGViIwBXThR2GIxUaH6Ydfru\n" +
            "lJEjlNK6aoyzbEtQY8IHVkyb7AP9mSLgbIcCQDs8au+xicFHbSBtC9sHh7gh12nC\n" +
            "O7R1sFW6+Kjf3uKe0P8FPXf72QvG/SBtCekq9I0BxgdVTxIKQdr527hybm0CQBy/\n" +
            "7N+tKa6mYJV6zL15wKt42UytsuAafYz6mqA2oMxIpBOb3jEFLiHWtk1wLo1QHW+O\n" +
            "zZShuN4Jhx43HC7C19kCQB0/bRoI2PEG4Zi0pZidVsoOagEMFpd8zBjXD3qzFzDt\n" +
            "Ugt1AtKUokaRaF+n0X1YOa3CTl5opZAm8Oclh/KyToQ=\n" +
            "-----END RSA PRIVATE KEY-----";

    @GetMapping("/hello")
    public String hello() {
        RsaSigner signer = new RsaSigner(ssoAppPrivateKey);
        return JwtHelper.encode("{\"username\":\"hua\",\"password\":\"1321\"}", signer).getEncoded();
    }
}
