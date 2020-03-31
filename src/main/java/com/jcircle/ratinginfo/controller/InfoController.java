package com.jcircle.ratinginfo.controller;

import com.jcircle.ratinginfo.response.InfoResponse;
import com.jcircle.ratinginfo.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



    @RestController("infoController")
    @Slf4j
    public class InfoController {



        private @Value("${app_name}")
        String appName;
        private @Value("${app_version}")
        String appVersion;
        private @Value("${app_release}")
        String appRelease;
        private String hostName;

        private String getHostName() {
            if (hostName == null) {
                hostName = CommonUtils.getHostNameFromSystem();
            }
            // log.info("HostName", hostName);
            // log.info("HostName :: {}", hostName);
            return hostName;
        }

        private InfoResponse getInfoResponse() {
            InfoResponse res = new InfoResponse();
            res.setAppName(appName);
            res.setAppVersion(appVersion);
            res.setAppRelease(appRelease);
            res.setHostName(getHostName());
            return res;
        }

        /**
         * GET /info.json
         */
        @CrossOrigin
        @GetMapping("/info.json")
        @ResponseBody
        public InfoResponse getInfoJson() {
            return getInfoResponse();
        }
    }


