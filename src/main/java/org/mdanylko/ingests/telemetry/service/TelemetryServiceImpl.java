package org.mdanylko.ingests.telemetry.service;

import io.grpc.stub.StreamObserver;
import org.mdanylko.ingests.telemetry.entity.CopyTelemetryRequestDto;
import org.mdanylko.ingests.telemetry.entity.CopyTelemetryResponseDto;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class TelemetryServiceImpl extends TelemetryServiceGrpc.TelemetryServiceImplBase {
    @Override
    public CopyTelemetryResponseDto processTelemetry(CopyTelemetryRequestDto telemetryReqDto, StreamObserver<CopyTelemetryResponseDto> telemetryResDto) {
        return null;
    }
}
