from dbAlco.serializers.alcohol_serializers import *
from rest_framework import generics, viewsets


class AlcoholViewSet(viewsets.ModelViewSet):
    queryset = Alcohol.objects.all()

    def get_serializer_class(self):
        if self.action in ("list", "retrieve"):
            return AlcoholGetSerializer
        else:
            return AlcoholModifySerializer


class AlcoholTypeViewSet(viewsets.ModelViewSet):
    queryset = AlcoholType.objects.all()
    serializer_class = AlcoholTypeSerializer


class AlcoholGeneralTypeViewSet(viewsets.ModelViewSet):
    queryset = AlcoholGeneralType.objects.all()
    serializer_class = AlcoholGeneralTypeSerializer
