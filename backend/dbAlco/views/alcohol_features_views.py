from dbAlco.serializers.alcohol_features_serializers import *
from rest_framework import generics, viewsets, filters


class CountryViewSet(viewsets.ModelViewSet):
    queryset = Country.objects.all()
    serializer_class = CountrySerializer
    filter_backends = [filters.OrderingFilter]


class ProducerViewSet(viewsets.ModelViewSet):
    queryset = Producer.objects.all()
    serializer_class = ProducerSerializer
    filter_backends = [filters.OrderingFilter]


class AlcoholRatingViewSet(viewsets.ModelViewSet):
    serializer_class = AlcoholRatingSerializer

    def get_queryset(self):
        return AlcoholRating.objects.filter(alcohol=self.request.query_params['alcohol'])
