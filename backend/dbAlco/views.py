from django.shortcuts import render

from rest_framework.mixins import (
    CreateModelMixin, ListModelMixin, RetrieveModelMixin, UpdateModelMixin
)
from rest_framework.viewsets import GenericViewSet

from .models import BeerType
from .models import Beer

from .serializers import BeerSerializer
from .serializers import BeerStyleSerializer


class BeerView(GenericViewSet,  # generic view functionality
               CreateModelMixin,  # handles POSTs
               RetrieveModelMixin,  # handles GETs for 1 Company
               UpdateModelMixin,  # handles PUTs and PATCHes
               ListModelMixin):  # handles GETs for many Companies

    serializer_class = BeerSerializer
    queryset = Beer.objects.all()
